package com.soj.booksharing.services;

import com.soj.booksharing.entity.Wishlist;
import com.soj.booksharing.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService{

    private final WishlistRepository wishlistRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }


    @Override
    public List<Wishlist> allWishes() {
       return wishlistRepository.findAll();
    }

    @Override
    public Wishlist wishById(Long id) {
        return wishlistRepository.findById(id).get();
    }
}
