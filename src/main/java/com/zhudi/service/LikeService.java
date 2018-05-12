package com.zhudi.service;

import com.zhudi.Utils.JedisAdapter;
import com.zhudi.Utils.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    JedisAdapter jedisAdapter;


    public int getLikeStatus(int userId, int entityType, int entityId){
        String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        if(jedisAdapter.sismember(likeKey, String.valueOf(userId))){
            return 1;
        }
        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityId, entityType);
        return jedisAdapter.sismember(disLikeKey, String.valueOf(userId)) ? -1 : 0;
    }

    public int getLikeCount(int entityType, int entityId){
        String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        return (int)jedisAdapter.scard(likeKey);
    }


    public long like(int userId, int entityType, int entityId){
        String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityId, entityType);
        if(jedisAdapter.sismember(likeKey, String.valueOf(userId))){
            jedisAdapter.srem(likeKey, String.valueOf(userId));
        }
        else{
            jedisAdapter.sadd(likeKey, String.valueOf(userId));
            jedisAdapter.srem(disLikeKey, String.valueOf(userId));
        }
        return jedisAdapter.scard(likeKey);
    }

    public long disLike(int userId, int entityType, int entityId){

        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityId, entityType);
        String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        if(jedisAdapter.sismember(disLikeKey, String.valueOf(userId))) {
            jedisAdapter.srem(disLikeKey, String.valueOf(userId));
        }
        else{
            jedisAdapter.sadd(disLikeKey, String.valueOf(userId));
            jedisAdapter.srem(likeKey, String.valueOf(userId));
        }
        return jedisAdapter.scard(likeKey);
    }
}
