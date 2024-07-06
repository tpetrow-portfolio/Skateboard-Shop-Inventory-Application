package com.example.demo.bootstrap;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

       /*
        OutsourcedPart o= new OutsourcedPart();
        o.setCompanyName("Western Governors University");
        o.setName("out test");
        o.setInv(5);
        o.setPrice(20.0);
        o.setId(100L);
        outsourcedPartRepository.save(o);
        OutsourcedPart thePart=null;
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("out test"))thePart=part;
        }

        System.out.println(thePart.getCompanyName());
        */
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        if(productRepository.count() == 0) { // if product list is empty, fill it with these 5 items
            Product skateboard = new Product(1,"Skateboard", 150.0, 15);
            Product longboard = new Product(2,"Longboard", 250.0, 15);
            Product cruiser = new Product(3,"Cruiser", 85.0, 15);
            Product penny = new Product(4,"Penny Board", 50.0, 15);
            Product oldschool = new Product(5,"Old-School", 100.0, 15);
            productRepository.save(skateboard);
            productRepository.save(longboard);
            productRepository.save(cruiser);
            productRepository.save(penny);
            productRepository.save(oldschool);
        }

        if(partRepository.count() == 0) { // if part list is empty, fill it with these 5 items
            Part wheels = new Part(11,"Wheels", 30.0, 100,5,1000);
            Part trucks = new Part(12, "Trucks", 60.0, 75,5,1000);
            Part bearings = new Part(13,"Bearings", 20.0, 100,5,1000);
            Part deck = new Part(14,"Deck", 80.0, 25,5,1000);
            Part kit = new Part(15,"Parts kit (Nuts, Screws, Bolts, etc.)", 8.0, 300,5,1000);
            partRepository.save(wheels);
            partRepository.save(trucks);
            partRepository.save(bearings);
            partRepository.save(deck);
            partRepository.save(kit);
        }


        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
