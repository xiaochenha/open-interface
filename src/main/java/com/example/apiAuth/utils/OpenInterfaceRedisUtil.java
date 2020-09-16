package com.example.apiAuth.utils;

import cn.hutool.db.nosql.redis.RedisDS;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class OpenInterfaceRedisUtil {

    Jedis jedis = RedisDS.create().getJedis();

    public boolean verifyMagic(String magicStr,String token){
        boolean existMagic = jedis.exists(magicStr);
        if(existMagic){
            String existToken = jedis.get(magicStr);
            if(existToken.equals(token)){
                return false;
            }
        }
        return true;
    }

    public void setMagic(String magicStr,String token){
        jedis.set(magicStr,token);
        jedis.expire(magicStr,60);
    }

}
