package com.demo.batch.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExampleItemWriter implements ItemWriter<Long> {
    @Override
    public void write(Chunk<? extends Long> items) throws Exception {
        System.out.println("Inside ItemWriter");
        items.forEach(System.out::println);
    }
}
