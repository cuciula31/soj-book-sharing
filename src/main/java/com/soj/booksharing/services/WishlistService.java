package com.soj.booksharing.services;

import com.soj.booksharing.entity.Wishlist;

import java.util.List;

public interface WishlistService {

    List<Wishlist> allWishes();

    Wishlist wishById(Long id);
}
