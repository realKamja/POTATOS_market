package com.poteto.repository;

import com.poteto.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Board_repository extends JpaRepository<Board, Long> {

}
