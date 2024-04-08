package it.trinakria.ecommerce.services;

import it.trinakria.ecommerce.model.dto.CategoryDto;
import it.trinakria.ecommerce.model.dto.ProductDto;
import it.trinakria.ecommerce.model.entities.Category;
import it.trinakria.ecommerce.model.entities.Product;
import it.trinakria.ecommerce.model.entities.User;
import it.trinakria.ecommerce.repository.CategoryRepo;
import it.trinakria.ecommerce.repository.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Value("${backend.image-url}")
    private String imageUrl;
    @Override
    public List<Product> getAll() {
        List<Product> products = productRepo.findAll();
        return products;
    }

    @Override
    public Product getById(Long id) {
        Product product = productRepo.findById(id).orElseGet(() -> {
            log.error("No product found for id {}", id);
            return new Product();
        });
        return product;
    }

    @Override
    public List<Product> getByCategory(Long id) {
        return productRepo.findItemsByCategoriesId(id);
    }

    @Override
    public Product create(ProductDto newProduct) {
        List<Category> categories = new ArrayList<>();
        for (Long c : newProduct.getCategories()) {
            categories.add(categoryRepo.findById(c).get());
        }
        Product product = new Product();
        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        product.setCategories(categories);
        product.setPrice(newProduct.getPrice());
        product.setImgUrls(newProduct.getImgUrls());
        product.setRatingCount(newProduct.getRatingCount());
        product.setRating(newProduct.getRating());
        return productRepo.save(product);
    }

    @Override
    public Product update(ProductDto newProduct, Long id) {
        Product product = productRepo.findById(id).get();
        /*List<Category> categories = new ArrayList<>();
        for (Category c : product.getCategories()) {
            categories.add(new Category(c.getName()));
        }*/
        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        //product.setCategories(categories);
        product.setPrice(newProduct.getPrice());
        product.setImgUrls(newProduct.getImgUrls());
        product.setUpdateAt(new Date());
        return productRepo.save(product);
    }

    @Override
    public Product setCategory(Long productId,Long categoryId) {
        Category newCat = categoryRepo.findById(categoryId).get();
        return productRepo.findById(productId)
                .map(product -> {
                    if(!product.getCategories().contains(newCat)) {
                        List<Category> categories = product.getCategories();
                        categories.add(newCat);
                        product.setCategories(categories);
                        product.setUpdateAt(new Date());
                    }
                    return productRepo.save(product);
                }).get();
    }

    @Override
    public Product removeCategory(Long productId, Long categoryId) {
        Category newCat = categoryRepo.findById(categoryId).get();
        return productRepo.findById(productId)
                .map(product -> {
                    if(product.getCategories().contains(newCat)) {
                        List<Category> categories = product.getCategories();
                        categories.remove(newCat);
                        product.setCategories(categories);
                        product.setUpdateAt(new Date());
                    }
                    return productRepo.save(product);
                }).get();
    }

    @Override
    public Product setRating(Long id, Double rating) {
        Product product = productRepo.findById(id).get();
        product.setRatingCount(product.getRatingCount()+1);
        Double ratingDef = (rating - product.getRating())/product.getRatingCount();
        long factor = (long) Math.pow(10, 2);
        ratingDef = ratingDef * factor;
        long tmp = Math.round(ratingDef);
        Double newRating = product.getRating() + ((double) tmp / factor);
        product.setRating(newRating);
        return productRepo.save(product);
    }

    @Override
    public List<String> getImages(Long id) {
        File[] imagesUrl = new File(imageUrl.concat("\\").concat(id.toString())).listFiles();
        List<String> imagesPath = new ArrayList<>();
        for(File f: imagesUrl){
            imagesPath.add(f.getAbsolutePath());
            System.out.println(f);
        }
        return imagesPath;
    }

    @Override
    public void delete(Long id) {
        productRepo.deleteById(id);
    }
}
