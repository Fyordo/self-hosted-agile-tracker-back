package com.fyordo.shatback.repos;

import com.fyordo.shatback.entities.ScrumColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ScrumColumnRepository extends JpaRepository<ScrumColumn, Long> {
    @Transactional
    @Modifying
    @Query("update ScrumColumn s set s.title = ?1, s.color = ?2 where s.id = ?3")
    void updateScrumColumn(String title, String color, Long id);
}