package com.vc.app.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCach<K, V> {

  private static final float loadFactor = 0.5f;

  private LinkedHashMap<K, V> cachMap;

  private int size = 16;

  public LRUCach(int size) {
    this.setSize(size);
    cachMap = new LinkedHashMap<K, V>(size, loadFactor, true) {

      /**
       * 
       */
      private static final long serialVersionUID = -3968507408760099730L;

      @Override
      protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        return LRUCach.this.size < cachMap.size();
      }
    };
  }

  public void put(K key, V value) {
    cachMap.put(key, value);
  }

  public V get(K key) {
    return this.cachMap.get(key);
  }

  public int getSize() {
    return this.size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public synchronized Collection<Map.Entry<K, V>> getAll() {
    return new ArrayList<Map.Entry<K, V>>(cachMap.entrySet());
  }

}
