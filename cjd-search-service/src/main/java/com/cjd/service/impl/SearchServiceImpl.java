package com.cjd.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjd.pojo.Item;
import com.cjd.pojo.ItemES;
import com.cjd.service.ManageService;
import com.cjd.service.SearchService;
import com.cjd.util.ItemUtils;
import com.cjd.util.JsonUtils;
import com.cjd.util.MapUtils;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {

	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;
	
	@Autowired
	private ManageService manageService;
	
	@Override
	public void initES() {
		List<Item> items = manageService.showAll();
		List<ItemES> itemESs = new ArrayList<ItemES>();
		//设置images
		for (Item item : items) {
			ItemES itemES = new ItemES(item.getId(), item.getTitle(), item.getSellPoint(), item.getPrice(), item.getNum(), item.getBarcode(), item.getImage(), item.getCid(), item.getStatus(), item.getCreated(), item.getUpdated(), null);
			String image = item.getImage();
			if(image != null && !"".equals(image)) {
				itemES.setImages(image.split(","));
			}
			itemESs.add(itemES);
		}
		
		for (ItemES item : itemESs) {
			ItemES obj = elasticsearchRestTemplate.queryForObject(GetQuery.getById(item.getId().toString()), ItemES.class);
			if(obj == null) {
				//存入es
				IndexQuery indexQuery = new IndexQueryBuilder()
					      .withId(item.getId().toString())
					      .withObject(item)
					      .build();
				String documentId = elasticsearchRestTemplate.index(indexQuery);
				System.out.println(documentId);
			}else {
				System.out.println("obj is exists!");
			}
		}
	}

	@Override
	public Map<String, Object> queryForES(String query, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		page = page - 1;
		Pageable pageable= PageRequest.of(page,rows);
		String preTag = "<span style='color:red'>";
		String postTag = "</span>";
		SearchQuery searchQuery=new NativeSearchQueryBuilder().
				withFilter(QueryBuilders.matchQuery("status","1")).
				withQuery(QueryBuilders.matchQuery("title",query)).
				withHighlightFields(new HighlightBuilder.Field("title").preTags(preTag).postTags(postTag))
				.build();
		searchQuery.setPageable(pageable);
		Page<ItemES> queryForPage = elasticsearchRestTemplate.queryForPage(searchQuery, ItemES.class, new SearchResultMapper() {
			
			@Override
			public <T> T mapSearchHit(SearchHit searchHit, Class<T> type) {
				return null;
			}
			
			@Override
			public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
				List<ItemES> chunk = new ArrayList<>();
				SearchHits hits = response.getHits();
				Long totalHits = hits.getTotalHits();//数据总数
				result.put("totalPages", totalHits%rows == 0?totalHits/rows : totalHits/rows + 1);
				for (SearchHit hit : hits) {
					if (hits.getHits().length <= 0) {
						return null;
					}
					ItemES itemES=new ItemES();
					//name or memoe
					HighlightField itemTitle = hit.getHighlightFields().get("title");
					if (itemTitle != null) {
						itemES.setTitle(itemTitle.fragments()[0].toString());
					}
					
					itemES.setId(Long.parseLong(hit.getId()));
					itemES.setImages(((List<String>) hit.getSourceAsMap().get("images")).toArray(new String [] {}));
					itemES.setSellPoint((String) hit.getSourceAsMap().get("sellPoint"));
					itemES.setPrice(Long.parseLong(hit.getSourceAsMap().get("price").toString()));
					chunk.add(itemES);
				}
				if (chunk.size() > 0) {
					return new AggregatedPageImpl<>((List<T>) chunk);
				}
				return new AggregatedPageImpl<>(new ArrayList<T> ());
			}
		});
		result.put("itemList", queryForPage.getContent());
		return result;
	}

	@Override
	public void insItemES(Item item) {
		ItemES itemES = ItemUtils.ItemChangeToES(item);
		IndexQuery indexQuery = new IndexQueryBuilder()
			      .withId(itemES.getId().toString())
			      .withObject(itemES)
			      .build();
		String documentId = elasticsearchRestTemplate.index(indexQuery);
		System.out.println(documentId);
	}

	@Override
	public void updateItemES(Item item) {
		String id = item.getId().toString();
		ItemES itemES = ItemUtils.ItemChangeToES(item);
		Map<String, Object> map;
		try {
			map = MapUtils.objectToMap(itemES);
			UpdateRequest updateRequest = new UpdateRequest("item", "_doc", id).doc(map);
			UpdateQuery query = new UpdateQueryBuilder()
					.withClass(ItemES.class)
					.withId(id)
					.withUpdateRequest(updateRequest)
					.build();
			UpdateResponse update = elasticsearchRestTemplate.update(query);
			System.out.println(update.getId());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ItemES findItemById(Long id) {
		ItemES obj = elasticsearchRestTemplate.queryForObject(GetQuery.getById(id.toString()), ItemES.class);
		return obj;
	}

	@Override
	public void delItemES(Item item) {
		String documentId = elasticsearchRestTemplate.delete(ItemES.class, item.getId().toString());
		System.out.println(documentId);
	}

}
