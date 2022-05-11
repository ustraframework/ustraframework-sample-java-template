package com.gsitm.ustra.java.sample.redis.controller;

import com.gsitm.ustra.java.sample.redis.repository.RedisTemplateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * redis sample Controller
 *
 * @author youngran.kwon@gsitm.com
 */
@Slf4j
@RequestMapping(value = "/redisTemplate")
@RestController
@AllArgsConstructor
public class RedisTemplateController {

    private RedisTemplateRepository redisTemplateRepository;

    /**
     * (K key, V value) 로 저장
     *
     * @param key
     * @param value
     */
    @GetMapping("/saveString")
    public void saveString(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
        redisTemplateRepository.saveString(key, value);
    }

    /**
     * (Object key) 로 조회
     *
     * @param key
     * @return
     */
    @GetMapping("/getString")
    public String getString(@RequestParam(value = "key") String key) {
        return redisTemplateRepository.getString(key);
    }

    /**
     * (K key, V... values) 로 저장
     *
     * @param key
     * @param values
     */
    @GetMapping("/saveSet")
    public void saveSet(@RequestParam(value = "key") String key, @RequestParam(value = "values") String values) {

        redisTemplateRepository.saveSet(key, values);
    }

    /**
     * (K key) 로 조회
     *
     * @param key
     * @return
     */
    @GetMapping("/getSet")
    public Set<String> getSet(@RequestParam(value = "key") String key) {
        return redisTemplateRepository.getSet(key);
    }

    /**
     * (H key, HK hashKey, HV value) 로 저장
     *
     * @param key
     * @param hashKey
     * @param value
     */
    @GetMapping("/saveHash")
    public void saveHash(@RequestParam(value = "key") String key, @RequestParam(value = "hashKey") String hashKey, @RequestParam(value = "value") String value) {
        redisTemplateRepository.saveHash(key, hashKey, value);
    }

    /**
     * (H key, Object hashKey) 로 조회
     *
     * @param key
     * @param hashKey
     * @return
     */
    @GetMapping("/getHash")
    public String getHash(@RequestParam(value = "key") String key, @RequestParam(value = "hashKey") String hashKey) {
        return redisTemplateRepository.getHash(key, hashKey);
    }

}
