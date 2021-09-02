package sb.sbredis.sbredis.config;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import sb.sbredis.sbredis.model.Customer;

@Configuration
@EnableRedisRepositories
public class Config {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.database}")
    private int database;

    @Value("${spring.redis.timeout}")
    private long timeout;

    @Value("${sbredis.key}")
    private String key;

    @Bean
  public RedisConnectionFactory connectionFactory() {
    return new JedisConnectionFactory();
  }

  @Bean
  public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

    RedisTemplate<byte[], byte[]> template = new RedisTemplate<byte[], byte[]>();
    template.setConnectionFactory(redisConnectionFactory);
    return template;
  }

  // @Bean
  // public StreamListener<String,ObjectRecord<String,Customer>> streamListener;


  // @Bean
  // public Subscription subscription(RedisConnectionFactory redisConnectionFsctory) throws UnknownHostException{
  //   var options = StreamMessageListenerContainer.StreamMessageListenerContainerOptions.builder().pollTimeout(Duration.ofMillis(timeout)).targetType(Customer.class).build();
  //   var listenerContainer = StreamMessageListenerContainer.create(redisConnectionFsctory,options);
  //   var subscription= listenerContainer.receiveAutoAck(
  //     Consumer.from(key, InetAddress.getLocalHost().getHostName()), 
  //     StreamOffset.create(key, ReadOffset.lastConsumed()),
  //      streamListener
  //     );
  //     listenerContainer.start();
  //     return subscription;
  // }
}
