package com.internship.coachbookingapi.datainitializer;

import com.internship.coachbookingapi.service.IDepartureService;
import com.internship.coachbookingapi.service.IDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Init implements CommandLineRunner {
    private final IDepartureService departureService;
    private final IDestinationService destinationService;

    @Autowired
    public Init(IDepartureService departureService, IDestinationService destinationService) {
        this.departureService = departureService;
        this.destinationService = destinationService;
    }

    @Override
    public void run(String... args) throws Exception {
//        Departure d1 = new Departure();
//        d1.setName("Đà Lạt");
//        d1.setSlug("da-lat");
//        departureService.save(d1);

//        Departure d1 = new Departure();
//        d1.setName("TP. HCM");
//        d1.setSlug("tp-ho-chi-minh");
//        departureService.save(d1);
//
//        Departure d2 = new Departure();
//        d2.setName("Cần Thơ");
//        d2.setSlug("can-tho");
//        departureService.save(d2);
//
//        Departure d3 = new Departure();
//        d3.setName("Di Linh");
//        d3.setSlug("di-linh");
//        departureService.save(d3);
//
//        Departure d4 = new Departure();
//        d4.setName("Bảo Lộc");
//        d4.setSlug("bao-loc");
//        departureService.save(d4);
//
//        Departure d5 = new Departure();
//        d5.setName("Madagui");
//        d5.setSlug("madagui");
//        departureService.save(d5);
//
//        Departure d6 = new Departure();
//        d6.setName("Bình Minh");
//        d6.setSlug("binh-minh");
//        departureService.save(d6);
//
//        Departure d7 = new Departure();
//        d7.setName("Phương Lâm - Định Quán");
//        d7.setSlug("phuong-lam-dinh-quan");
//        departureService.save(d7);
//
//        Departure d8 = new Departure();
//        d8.setName("Đức Trọng");
//        d8.setSlug("duc-trong");
//        departureService.save(d8);

//        Departure d9 = new Departure();
//        d9.setName("Tân Phú");
//        d9.setSlug("tan-phu");
//        departureService.save(d9);

//        Destination d2 = new Destination();
//        d2.setName("TP. HCM");
//        d2.setSlug("tp-ho-chi-minh");
//        destinationService.save(d2);
//
//        Destination d3 = new Destination();
//        d3.setName("Cần Thơ");
//        d3.setSlug("can-tho");
//        destinationService.save(d3);
//
//        Destination d4 = new Destination();
//        d4.setName("Di Linh");
//        d4.setSlug("di-linh");
//        destinationService.save(d4);
//
//        Destination d5 = new Destination();
//        d5.setName("Bảo Lộc");
//        d5.setSlug("bao-loc");
//        destinationService.save(d5);
//
//        Destination d6 = new Destination();
//        d6.setName("Madagui");
//        d6.setSlug("madagui");
//        destinationService.save(d6);
//
//        Destination d7 = new Destination();
//        d7.setName("Bình Minh");
//        d7.setSlug("binh-minh");
//        destinationService.save(d7);
//
//        Destination d8 = new Destination();
//        d8.setName("Phương Lâm - Định Quán");
//        d8.setSlug("phuong-lam-dinh-quan");
//        destinationService.save(d8);
//
//        Destination d9 = new Destination();
//        d9.setName("Đức Trọng");
//        d9.setSlug("duc-trong");
//        destinationService.save(d9);

//        Destination d9 = new Destination();
//        d9.setName("Tân Phú");
//        d9.setSlug("tan-phu");
//        destinationService.save(d9);
    }
}
