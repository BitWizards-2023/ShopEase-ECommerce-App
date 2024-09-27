package com.example.shopease.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shopease.R;
import com.example.shopease.activities.ProductDetailsActivity;
import com.example.shopease.adapters.CarouselAdapter;
import com.example.shopease.adapters.CategoryAdapter;
import com.example.shopease.adapters.ProductAdapter;
import com.example.shopease.models.Category;
import com.example.shopease.models.Product;
import com.example.shopease.models.Vendor;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView, categoryRecyclerView;
    private ProductAdapter productAdapter;
    private CategoryAdapter categoryAdapter;
    private List<Product> productList;
    private List<Category> categoryList;
    private ViewPager2 viewPager;
    private CarouselAdapter carouselAdapter;
    private List<String> carouselImages;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Set up the RecyclerView for product cards
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        // Initialize dummy vendor and product data
        Vendor vendor1 = new Vendor("Vendor 1", "Best in electronics", "9 AM - 9 PM");
        Vendor vendor2 = new Vendor("Vendor 2", "Best in cosmetics", "10 AM - 6 PM");


        // Initialize dummy product data
        productList = new ArrayList<>();
        productList.add(new Product("Product 1", "$20", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTOZUfCF7SyZxiR6aC-w6RUK3oNCFvkzeO4VQ&s", vendor1));
        productList.add(new Product("Product 2", "$40", "https://img.freepik.com/premium-photo/ultra-realistic-orange-background-4k-hd-photo-product_1193781-21514.jpg", vendor2));
        productList.add(new Product("Product 3", "$60", "https://img.freepik.com/free-photo/3d-beauty-product-studio_23-2151401472.jpg", vendor1));
        productList.add(new Product("Product 4", "$80", "https://img.freepik.com/free-photo/organic-cosmetic-product-with-dreamy-aesthetic-fresh-background_23-2151382816.jpg", vendor2));

        // Set up the product adapter with click listener
        productAdapter = new ProductAdapter(getActivity(), productList, new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                // Navigate to ProductDetailsActivity and pass product data
                Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                intent.putExtra("productName", product.getName());
                intent.putExtra("productPrice", product.getPrice());
                intent.putExtra("productImage", product.getImageUrl());
                intent.putExtra("vendor", product.getVendor());  // Pass the Vendor object
                startActivity(intent);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(productAdapter);

        // Set up the ViewPager2 for carousel
        viewPager = view.findViewById(R.id.view_pager);
        carouselImages = new ArrayList<>();
        carouselImages.add("https://img.freepik.com/free-psd/black-friday-super-sale-web-banner-template_106176-1672.jpg?w=996&t=st=1726637866~exp=1726638466~hmac=39a94df74243b8aa64f7cadaa5f7dd73e6172e40c2bad8fbf4e10dd46aa34038");
        carouselImages.add("https://img.freepik.com/free-psd/black-friday-super-sale-web-banner-template_120329-3861.jpg?w=1060&t=st=1726637788~exp=1726638388~hmac=99476cd0a0a75595a427f1f09e8185ada49c4d514114f99ca9c628e5ca9ba1bd");
        carouselImages.add("https://img.freepik.com/free-psd/black-friday-super-sale-facebook-cover-template_106176-1555.jpg?w=1380&t=st=1726637992~exp=1726638592~hmac=3845ee4ffb845f66657123f8f8999a2f06d3cdf5044f06513905bc3737a47f70");

        // Set up the carousel adapter and link with dots indicator
        carouselAdapter = new CarouselAdapter(getActivity(), carouselImages);
        viewPager.setAdapter(carouselAdapter);

        // Auto-scroll the ViewPager2
        autoScrollViewPager();

        // Set up the RecyclerView for category cards (horizontal scrolling)
        categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
        LinearLayoutManager categoryLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(categoryLayoutManager);

        // Initialize dummy category data
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Electronics", "https://www.codrey.com/wp-content/uploads/2017/12/Consumer-Electronics.png"));
        categoryList.add(new Category("Fashion", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqtfoMRBIbztGVc6RJ8_ld3YbcINKogb29Yg&s"));
        categoryList.add(new Category("Home & Kitchen", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQoXMCHARa2hp7f_b-_p0SUDeH1eX_uu5Rhw&s"));
        categoryList.add(new Category("Beauty", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdnqVnRmEgl0GnV06ujlPzwycOrtSnxxxXTA&s"));

        // Set up the category adapter
        categoryAdapter = new CategoryAdapter(getActivity(), categoryList);
        categoryRecyclerView.setAdapter(categoryAdapter);

        return view;
    }

    // Function to auto-scroll the ViewPager2 (carousel)
    private void autoScrollViewPager() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int totalItems = carouselAdapter.getItemCount();
                if (currentItem < totalItems - 1) {
                    viewPager.setCurrentItem(currentItem + 1);
                } else {
                    viewPager.setCurrentItem(0); // Loop back to the first item
                }
                handler.postDelayed(this, 3000); // Change image every 3 seconds
            }
        };
        handler.postDelayed(runnable, 3000);
    }
}
