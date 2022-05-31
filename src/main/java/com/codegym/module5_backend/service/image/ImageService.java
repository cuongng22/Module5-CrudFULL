package com.codegym.module5_backend.service.image;

import com.codegym.module5_backend.model.entity.Image;
import com.codegym.module5_backend.repository.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService implements IImageService{
    @Autowired private IImageRepository iImageRepository;

    @Override
    public Page<Image> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Image> findAll() {
        return iImageRepository.findAll();
    }

    @Override
    public Optional<Image> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Image save(Image image) {
        return iImageRepository.save(image);
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public List<Image> findImageByProduct(Long id) {
        return iImageRepository.findImageByProduct(id);
    }

    @Override
    public void deleteImageByProductId(Long productId) {
        iImageRepository.deleteImageByProductId(productId);
    }
}
