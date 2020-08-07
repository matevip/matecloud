package vip.mate.core.elasticsearch.core;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;

/**
 * ElasticSearch封装类
 * @author pangu
 */
public class ElasticSearchTemplate extends ElasticsearchRestTemplate {
    public ElasticSearchTemplate(RestHighLevelClient client) {
        super(client);
    }

    public ElasticSearchTemplate(RestHighLevelClient client, ElasticsearchConverter elasticsearchConverter) {
        super(client, elasticsearchConverter);
    }
}
