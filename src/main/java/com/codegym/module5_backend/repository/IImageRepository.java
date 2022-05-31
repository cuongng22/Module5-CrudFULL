package com.codegym.module5_backend.repository;

import com.codegym.module5_backend.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IImageRepository extends JpaRepository<Image, Long> {
    @Query(value = "select * from image where product_id = ?1", nativeQuery = true)
    List<Image> findImageByProduct(Long id);
    @Modifying
    @Query(value = "delete from image where product_id = ?1", nativeQuery = true)
    void deleteImageByProductId(Long productId);
}
