package pl.damian.shop.config;

import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.damian.shop.domain.dao.Product;

@Configuration
public class HazelcastConfig {

    @Bean
    public Config configHazelcast() {

        Config product = new Config()
                .setInstanceName("hazelcast-instance")
                .addMapConfig(new MapConfig()
                        .setName("product")
                        .setEvictionConfig(new EvictionConfig()
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setSize(10)
                                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE))
                        .setTimeToLiveSeconds(60 * 60 * 24));

        product.getSerializationConfig().addPortableFactory
                (1, (int id) -> (id == 1) ? new Product() : null);
        return product;
    }


}
