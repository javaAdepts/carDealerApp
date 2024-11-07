package com.binary.carDealer;

import com.binary.carDealer.entities.Car;
import com.binary.carDealer.entities.Dealer;
import com.binary.carDealer.entities.Member;
import com.binary.carDealer.enums.UserRole;
import com.binary.carDealer.repositories.CarRepository;
import com.binary.carDealer.repositories.DealerRepository;
import com.binary.carDealer.repositories.MemberRepository;
import com.binary.carDealer.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CarDealerApplication implements CommandLineRunner {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private DealerRepository dealerRepository;

	@Autowired
	private MemberService  memberService;


	public static void main(String[] args) {
		SpringApplication.run(CarDealerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// creating the dealers
		Dealer dealer1 = new Dealer("toyota", List.of());
		Dealer dealer2 = new Dealer("binary", List.of());

		// saving the dealers
		Dealer savedDealer1 = dealerRepository.save(dealer1);
		Dealer savedDealer2 = dealerRepository.save(dealer2);

		// creating the car object
		Car car1 = new Car("Toyota", "camry", "red", "VA1234", 2024, 35000, dealer1);
		Car car2 = new Car("Honda", "CRV", "white", "Va0987", 2024, 39000, savedDealer2);
		Car car3 = new Car("Tesla", "model3", "blue", "VA5678", 2024, 40000, savedDealer2);

//		car1.getDealer() != null && car1.getDealer().getDealerID();

		// saving the cars
		carRepository.save(car2);
		carRepository.save(car1);
		carRepository.save(car3);


		Member member1 = new Member("nate", "nate@binary.com", "1234", UserRole.USER.toString());
		Member member2 = new Member("ruth", "ruth@binary.com", "0987", UserRole.ADMIN.toString());


		memberService.saveMember(member1);
		memberService.saveMember(member2);


	}
}
