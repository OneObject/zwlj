import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.util.List;

public class ElasticSearchTest {

    TransportClient client;

    @Before
    public void before() {
        Settings settings = Settings.builder()
                //.put("cluster.name", "my-application")    //指定集群名称
                .put("client.transport.sniff", true)    //启动嗅探功能
                .build();

        /*
         * 2：创建客户端
         * 通过setting来创建，若不指定则默认链接的集群名为elasticsearch
         * 链接使用tcp协议即9300
         */
        client = new PreBuiltTransportClient(settings);
        TransportAddress address = new InetSocketTransportAddress(new InetSocketAddress("127.0.0.1", 9300));
        client.addTransportAddress(address);

        /*
         * 3：查看集群信息
         * 注意我的集群结构是：
         *   131的elasticsearch.yml中指定为主节点不能存储数据，
         *   128的elasticsearch.yml中指定不为主节点只能存储数据。
         * 所有控制台只打印了192.168.79.128,只能获取数据节点
         *
         */
        List<DiscoveryNode> list = client.connectedNodes();
        for (DiscoveryNode node : list) {
            System.out.println(node.getHostAddress());
        }
    }

    /**
     * 通过prepareGet方法获取指定文档信息
     */
    @Test
    public void testGet() {
        GetResponse response = client.prepareGet("logstash-2016.04.05", "system_log", "AVwz6BrT4Ps-dmkKVcMI").get();
        System.out.println(response);
    }

    /**
     * 通过prepareSearch查询索引库
     * setQuery(QueryBuilders.matchQuery("name", "jack"))
     * setSearchType(SearchType.QUERY_THEN_FETCH)
     *
     */
    @Test
    public void testQuery() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.queryStringQuery("verb := post").analyzeWildcard(true));
        boolQuery.must(QueryBuilders.rangeQuery("@timestamp").gte(1496114008737l).lte(1496718808737l).format("epoch_millis"));

        SearchResponse searchResponse = client.prepareSearch("logstash-*")
                .setTypes("system_log")
                .setSize(0)
                .setQuery(boolQuery)
                .addAggregation(AggregationBuilders.dateHistogram("agg1").field("@timestamp").dateHistogramInterval(DateHistogramInterval.hours(3)).minDocCount(0).timeZone(DateTimeZone.forID("Asia/Shanghai")))
                .execute().actionGet();
        searchResponse.getAggregations().get("agg1");
        System.out.println(searchResponse);
    }
}
