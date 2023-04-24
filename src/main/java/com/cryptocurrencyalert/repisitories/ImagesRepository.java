package com.cryptocurrencyalert.repisitories;

import com.cryptocurrencyalert.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<Image,Integer> {
}
