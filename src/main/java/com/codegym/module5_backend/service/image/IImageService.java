package com.codegym.module5_backend.service.image;

import com.codegym.module5_backend.model.entity.Image;
import com.codegym.module5_backend.service.IGeneralService;

import java.util.List;

public interface IImageService extends IGeneralService<Image> {
    List<Image> findImageByProduct(Long id);

    void deleteImageByProductId(Long productId);
}
