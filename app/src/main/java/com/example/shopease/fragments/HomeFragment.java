package com.example.shopease.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.example.shopease.models.ProductResponse;
import com.example.shopease.models.Vendor;
import com.example.shopease.models.CategoryResponse;
import com.example.shopease.network.ApiService;
import com.example.shopease.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        // Fetch categories from the backend
        fetchCategories();

        //Fetch product data from backend
        fetchProducts();

        return view;
    }

    // Fetch categories from the backend API
    private void fetchCategories() {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        Call<CategoryResponse> call = apiService.getCategories();

        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Populate the category list with data from the API response
                    categoryList = new ArrayList<>();
                    for (CategoryResponse.CategoryData categoryData : response.body().getData()) {
                        categoryList.add(new Category(
                                categoryData.getId(),
                                categoryData.getName(),
                                categoryData.getImageUrl()
                        ));
                    }

                    // Set up the category adapter with fetched data
                    categoryAdapter = new CategoryAdapter(getActivity(), categoryList);
                    categoryRecyclerView.setAdapter(categoryAdapter);
                } else {
                    Toast.makeText(getActivity(), "Failed to load categories", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Fetch product data from the backend API
    private void fetchProducts() {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        Call<ProductResponse> call = apiService.getProducts();

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Populate the product list with data from the API response
                    productList = new ArrayList<>();
                    for (ProductResponse.ProductData productData : response.body().getData()) {
                        if(productData.isActive()){
                            productList.add(new Product(
                                    productData.getName(),
                                    String.valueOf(productData.getPrice()),  // Assuming price is int, so converting to String
                                    productData.getImages().get(0),  // Assuming at least one image is available
                                    productData.getVendorId(),  // Store vendorId instead of the full vendor object
                                    productData.getDescription()
                            ));
                        }
                    }

                    // Set up the product adapter with click listener
                    productAdapter = new ProductAdapter(getActivity(), productList, new ProductAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Product product) {
                            // Navigate to ProductDetailsActivity and pass product data
                            Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                            intent.putExtra("productName", product.getName());
                            intent.putExtra("productPrice", product.getPrice());
                            intent.putExtra("productImage", product.getImageUrl());
                            intent.putExtra("vendorId", product.getVendorId());  // Pass only the vendor ID
                            intent.putExtra("productDescription", product.getDescription());
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(productAdapter);
                } else {
                    Toast.makeText(getActivity(), "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
