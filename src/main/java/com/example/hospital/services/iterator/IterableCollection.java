package com.example.hospital.services.iterator;

public interface IterableCollection<T> {
    Iterator<T> createIterator(); 
}
