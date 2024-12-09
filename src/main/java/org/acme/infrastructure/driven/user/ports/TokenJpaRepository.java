package org.acme.infrastructure.driven.user.ports;
import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.ReactiveKeyCommands;
import io.quarkus.redis.datasource.string.StringCommands;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TokenJpaRepository {
    private ReactiveKeyCommands<String> keys;
    private StringCommands<String, Integer> cmd;
    private RedisDataSource redisDS;

    public TokenJpaRepository(RedisDataSource redisDS, ReactiveRedisDataSource reactiveRedisDS){
        this.redisDS = redisDS;
        this.keys = reactiveRedisDS.key();
        this.cmd = redisDS.string(Integer.class);
    }

    Uni<Void> del(String key){
        return keys.del(key)
                .replaceWithVoid();
    }

    public int get(String key){
        return cmd.get(key);
    }

    public void set(String key, int value){
        cmd.set(key, value);
    }

   public void increment(String key, int incrementBy){
        System.out.println("Key: " + key);
        cmd.incrby(key, incrementBy);
   }

   String execute(String command, String param) {
        return redisDS.execute(command, param).toString();
   }

   Uni<List<String>> keys(){
        return keys
                .keys("");
   }

}
