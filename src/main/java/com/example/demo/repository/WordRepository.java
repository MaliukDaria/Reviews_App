package com.example.demo.repository;

import com.example.demo.model.Word;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WordRepository extends CrudRepository<Word, Long> {
    @Query("SELECT word FROM Word ORDER BY amount DESC, word")
    List<String> getMostUsedWords(Pageable pageable);
}
