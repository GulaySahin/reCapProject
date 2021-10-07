package com.etiya.reCapProject.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etiya.reCapProject.business.abstracts.AdditionalServicesService;
import com.etiya.reCapProject.business.abstracts.CarService;
import com.etiya.reCapProject.business.abstracts.CreatePosService;
import com.etiya.reCapProject.business.abstracts.CreditCardService;
import com.etiya.reCapProject.business.abstracts.CustomerFindeksPointCheckService;
import com.etiya.reCapProject.business.abstracts.RentAlService;
import com.etiya.reCapProject.business.constants.messages.RentalMessages;
import com.etiya.reCapProject.core.utilities.businnes.BusinnesRules;
import com.etiya.reCapProject.core.utilities.results.DataResult;
import com.etiya.reCapProject.core.utilities.results.ErrorResult;
import com.etiya.reCapProject.core.utilities.results.Result;
import com.etiya.reCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.reCapProject.core.utilities.results.SuccessResult;
import com.etiya.reCapProject.dataAccess.abstracts.AdditionalServiceDao;
import com.etiya.reCapProject.dataAccess.abstracts.CarDao;
import com.etiya.reCapProject.dataAccess.abstracts.CareDao;
import com.etiya.reCapProject.dataAccess.abstracts.CorporateCustomerDao;
import com.etiya.reCapProject.dataAccess.abstracts.IndividualCustomerDao;
import com.etiya.reCapProject.dataAccess.abstracts.RentAlDao;
import com.etiya.reCapProject.entities.concretes.AdditionalService;
import com.etiya.reCapProject.entities.concretes.Car;
import com.etiya.reCapProject.entities.concretes.Care;
import com.etiya.reCapProject.entities.concretes.CorporateCustomer;
import com.etiya.reCapProject.entities.concretes.IndividualCustomer;
import com.etiya.reCapProject.entities.concretes.RentAl;
import com.etiya.reCapProject.entities.dtos.AdditionalServiceDto;
import com.etiya.reCapProject.entities.dtos.CreditCardDto;
import com.etiya.reCapProject.entities.dtos.RentalDetailDto;
import com.etiya.reCapProject.entities.requests.creditCardRequest.AddCreditCardRequest;
import com.etiya.reCapProject.entities.requests.posRequest.PosRequest;
import com.etiya.reCapProject.entities.requests.rentalRequest.AddRentAlRequest;
import com.etiya.reCapProject.entities.requests.rentalRequest.DeleteRentAlRequest;
import com.etiya.reCapProject.entities.requests.rentalRequest.UpdateRentAlRequest;

@Service
public class RentAlManager implements RentAlService{
	
	private RentAlDao rentAlDao;
	private CarDao carDao;
	private CorporateCustomerDao corporateCustomerDao;
	private IndividualCustomerDao individualCustomerDao;
	private CustomerFindeksPointCheckService checkService;
	private CareDao careDao;
	private CreditCardService creditCardService;
	private CreatePosService createPosService;
	private CarService carService;
	private AdditionalServiceDao additionalServiceDao;
	private AdditionalServicesService additionalServicesService;
	private ModelMapper modelMapper;
	
	
	
	@Autowired
	public RentAlManager(RentAlDao rentAlDao, CarDao carDao,CorporateCustomerDao corporateCustomerDao,
			IndividualCustomerDao individualCustomerDao,
			CreditCardService creditCardService,
			CreatePosService createPosService,
			CareDao careDao,
			AdditionalServiceDao additionalServiceDao,
			CarService carService,
			AdditionalServicesService additionalServicesService,
			ModelMapper modelMapper,
			CustomerFindeksPointCheckService checkService) {
		super();
		this.rentAlDao = rentAlDao;
		this.carDao=carDao;
		this.corporateCustomerDao=corporateCustomerDao;
		this.individualCustomerDao=individualCustomerDao;
		this.checkService=checkService;
		this.careDao=careDao;
		this.additionalServiceDao=additionalServiceDao;
		this.creditCardService=creditCardService;
		this.createPosService= createPosService;
		this.carService=carService;
		this.modelMapper=modelMapper;
		this.additionalServicesService=additionalServicesService;
	}

	@Override
	public DataResult<List<RentalDetailDto>> getAll() {
		List<RentAl> rentals= this.rentAlDao.findAll();
		
		List<RentalDetailDto> rentalDetailDtos=new ArrayList<RentalDetailDto>();
		
		for (RentAl rental : rentals) {
			
			RentalDetailDto rentalDetailDto= modelMapper.map(rental, RentalDetailDto.class);
			
			rentalDetailDto.setGetCarDetailDto(this.carService.getById(rental.getCar().getCarId()).getData());
			rentalDetailDto.setAdditionalServiceDetailDtos(this.additionalServicesService.getByRentalId(rental.getRentAlId()).getData());
			rentalDetailDto.setCustomerId(this.rentAlDao.getById(rental.getRentAlId()).getCustomers().getId());
			
			rentalDetailDtos.add(rentalDetailDto);
		}

		return new SuccessDataResult<List<RentalDetailDto>>(rentalDetailDtos,RentalMessages.GetAll);
	}
	
	@Override
	public DataResult<RentalDetailDto> getById(int id) {
		
		RentAl rental = this.rentAlDao.getById(id);
		
		RentalDetailDto rentalDetailDto = modelMapper.map(rental, RentalDetailDto.class);		
		rentalDetailDto.setGetCarDetailDto(this.carService.getById(rental.getCar().getCarId()).getData());
		rentalDetailDto.setAdditionalServiceDetailDtos(this.additionalServicesService.getByRentalId(rental.getRentAlId()).getData());
		rentalDetailDto.setCustomerId(this.rentAlDao.getById(rental.getRentAlId()).getCustomers().getId());
		
		return new SuccessDataResult<RentalDetailDto>(rentalDetailDto, RentalMessages.GetById);
	}
	
	
	@Override
	@Transactional
	public Result addRentalForIndividualCustomer(AddRentAlRequest addRentAlRequest) {
		Car car = this.carDao.getById(addRentAlRequest.getCarId());

		IndividualCustomer individualCustomer = new IndividualCustomer();
		individualCustomer.setId(addRentAlRequest.getCustomerId());

		RentAl rentAl = new RentAl();
		rentAl.setRentDate(addRentAlRequest.getRentDate());
		rentAl.setReturnDate(addRentAlRequest.getReturnDate());

		//Arabanın kiralanmadan önceki ili kiralanma şehri olacak
		rentAl.setTakeCity(car.getCity());

		// Arabanın kiralanmadan önceki ili kiralanma şehri olacak
		rentAl.setStartKilometer(car.getKilometer());

		rentAl.setEndKilometer(addRentAlRequest.getEndKilometer());
		rentAl.setReturnCity(addRentAlRequest.getReturnCity());

		// Ek hizmetleri hesaplama methodu.
		rentAl.setAmount(checkCalculateTotalAmountOfRental(addRentAlRequest));

		// Müşterinin girdiği km bilgisini arabanın km bilgisi ile güncelleme
		car.setKilometer(addRentAlRequest.getEndKilometer() + car.getKilometer());

		rentAl.setCar(car);
		rentAl.setCustomers(individualCustomer);

		// iş motoru
		var result = BusinnesRules.run(checkCarIsSubmit(rentAl.getCar().getCarId()),
				checkIndiviualCustomerFindexPoint(this.individualCustomerDao.getById(addRentAlRequest.getCustomerId()),
						this.carDao.getById(addRentAlRequest.getCarId())),
				checkIsCarCare(addRentAlRequest.getCarId()),
				this.checkPaymentControl(addRentAlRequest.getCreditCardDto(), getRentalTotalPrice(addRentAlRequest)));

		if (result != null) {
			return result;
		}

		this.rentAlDao.save(rentAl);
		
		// Arabayı tekrar teslim ettiğimizde teslim ili arabanın bulunduğu il olacak
		car.setCity(addRentAlRequest.getReturnCity());
		this.carDao.save(car);

		// Müşteri kredi kartını kaydetmek istiyorsa kaydetme işlemi
		if (addRentAlRequest.isSaveCreditCard()) {
			this.checkCreditCardPaymentAndSavedControl(addRentAlRequest.getCreditCardDto(),
					addRentAlRequest.getCustomerId());
		}
		return new SuccessResult(RentalMessages.Add);

	}

	@Override
	public Result updateRentalForIndividualCustomer(UpdateRentAlRequest updateRentAlRequest) {
		
		var result = BusinnesRules.run(
				checkIndiviualCustomerFindexPoint(
						this.individualCustomerDao.getById(updateRentAlRequest.getCustomerId()),
						this.carDao.getById(updateRentAlRequest.getCarId())),
				checkIsCarCare(updateRentAlRequest.getCarId()));

		if (result != null) {
			return result;
		}

		Car car = this.carDao.getById(updateRentAlRequest.getCarId());

		IndividualCustomer individualCustomer = this.individualCustomerDao.getById(updateRentAlRequest.getCustomerId());
		individualCustomer.setId(updateRentAlRequest.getCustomerId());

		RentAl rentAl = this.rentAlDao.getById(updateRentAlRequest.getRentAlId());
		rentAl.setRentDate(updateRentAlRequest.getRentDate());
		rentAl.setReturnDate(updateRentAlRequest.getReturnDate());
		rentAl.setEndKilometer(updateRentAlRequest.getEndKilometer());
		rentAl.setReturnCity(updateRentAlRequest.getReturnCity());
		rentAl.setAdditionalServices(
				this.convertFromAdditionalServiceDto(updateRentAlRequest.getAdditionalServiceDtos()));

		car.setKilometer(updateRentAlRequest.getEndKilometer() + car.getKilometer());

		rentAl.setCar(car);
		rentAl.setCustomers(individualCustomer);

		this.rentAlDao.save(rentAl);

		car.setCity(updateRentAlRequest.getReturnCity());
		this.carDao.save(car);

		return new SuccessResult(RentalMessages.Update);
	}

	@Override
	@Transactional
	public Result addRentalForCorporateCustomer(AddRentAlRequest addRentAlRequest) {
		Car car = this.carDao.getById(addRentAlRequest.getCarId());

		CorporateCustomer corporateCustomer = new CorporateCustomer();
		corporateCustomer.setId(addRentAlRequest.getCustomerId());

		var result = BusinnesRules.run(checkCarIsSubmit(addRentAlRequest.getCarId()),
				checkCorporateCustomerFindexPoint(this.corporateCustomerDao.getById(addRentAlRequest.getCustomerId()),
						this.carDao.getById(addRentAlRequest.getCarId())),
				checkIsCarCare(addRentAlRequest.getCarId()),
				this.checkPaymentControl(addRentAlRequest.getCreditCardDto(), getRentalTotalPrice(addRentAlRequest)));

		if (result != null) {
			return result;
		}

		RentAl rentAl = new RentAl();
		rentAl.setRentDate(addRentAlRequest.getRentDate());
		rentAl.setReturnDate(addRentAlRequest.getReturnDate());
		rentAl.setStartKilometer(car.getKilometer());
		rentAl.setEndKilometer(addRentAlRequest.getEndKilometer());
		
		//Ek Hizmet Methodu
		rentAl.setAmount(checkCalculateTotalAmountOfRental(addRentAlRequest));

		// Arabanın kiralanmadan önceki ili kiralanma şehri olacak
		rentAl.setTakeCity(car.getCity());

		rentAl.setReturnCity(addRentAlRequest.getReturnCity());
		
		// Müşterinin girdiği km bilgisini arabanın km bilgisi ile güncelleme
		car.setKilometer(addRentAlRequest.getEndKilometer() + car.getKilometer());

		rentAl.setCar(car);
		rentAl.setCustomers(corporateCustomer);

		this.rentAlDao.save(rentAl);

		// Arabayı tekrar teslim ettiğimizde teslim ili arabanın bulunduğu il olacak
		car.setCity(addRentAlRequest.getReturnCity());
		this.carDao.save(car);
		
		// Müşteri kredi kartını kaydetmek istiyorsa kaydetme işlemi
		if (addRentAlRequest.isSaveCreditCard()) {
			this.checkCreditCardPaymentAndSavedControl(addRentAlRequest.getCreditCardDto(),
					addRentAlRequest.getCustomerId());
		}
		return new SuccessResult(RentalMessages.Add);
		
		
	}

	@Override
	public Result updateRentalForCorporateCustomer(UpdateRentAlRequest updateRentAlRequest) {
		
		var result = BusinnesRules.run(checkCarIsSubmit(updateRentAlRequest.getCarId()),
				checkCorporateCustomerFindexPoint(
						this.corporateCustomerDao.getById(updateRentAlRequest.getCustomerId()),
						this.carDao.getById(updateRentAlRequest.getCarId())),
				checkIsCarCare(updateRentAlRequest.getCarId()));

		if (result != null) {
			return result;
		}

		Car car = this.carDao.getById(updateRentAlRequest.getCarId());

		CorporateCustomer corporateCustomer = new CorporateCustomer();
		corporateCustomer.setId(updateRentAlRequest.getCustomerId());

		RentAl rentAl = this.rentAlDao.getById(updateRentAlRequest.getRentAlId());
		rentAl.setRentAlId(updateRentAlRequest.getRentAlId());
		rentAl.setRentDate(updateRentAlRequest.getRentDate());
		rentAl.setReturnDate(updateRentAlRequest.getReturnDate());
		rentAl.setEndKilometer(updateRentAlRequest.getEndKilometer());
		rentAl.setReturnCity(updateRentAlRequest.getReturnCity());

		rentAl.setAdditionalServices(
				this.convertFromAdditionalServiceDto(updateRentAlRequest.getAdditionalServiceDtos()));

		car.setKilometer(updateRentAlRequest.getEndKilometer() + car.getKilometer());

		rentAl.setCar(car);
		rentAl.setCustomers(corporateCustomer);

		this.rentAlDao.save(rentAl);

		car.setCity(updateRentAlRequest.getReturnCity());
		this.carDao.save(car);
		return new SuccessResult(RentalMessages.Update);
	}
	
	
	
	@Override
	public Result delete(DeleteRentAlRequest deleteRentAlRequest) {
		RentAl rentAl= this.rentAlDao.getById(deleteRentAlRequest.getRentAlId());
		
		this.rentAlDao.delete(rentAl);		
		return new SuccessResult(RentalMessages.Delete);
	}
	
	
	
	//arabanın return datesi null ise araba müsait değildir
	public Result checkCarIsSubmit(int carId) {
		for (RentAl rental : this.rentAlDao.getByCar_CarId(carId)) {
			if(rental.getReturnDate() == null ) {
				return new ErrorResult(RentalMessages.ErrorIfCarIsNotAvailableToRent);
			}
		}
		return new SuccessResult();
	}
	
	//Bireysel müşterinin kiralama için findeks skoru yeterli mi?
	private Result checkIndiviualCustomerFindexPoint(IndividualCustomer individualCustomer,Car car) {
		
		if (this.checkService.checkIndividualCustomerFindexPoint(individualCustomer) <=
				car.getFindexPoint()) {
			return new ErrorResult(RentalMessages.ErrorFindeksScore);
		}
		
		return new SuccessResult();
		
		
	}
	
	//Kurumsal müşterinin kiralama için findeks skoru yeterli mi?
	private Result checkCorporateCustomerFindexPoint(CorporateCustomer corporateCustomer, Car car) {

		if (this.checkService.checkCorporateCustomerFindexPoint(corporateCustomer) <= car.getFindexPoint()) {
			return new ErrorResult(RentalMessages.ErrorFindeksScore);
		}

		return new SuccessResult();

	}
	
	//araba bakımda ise kiralanamaz methodu
	private Result checkIsCarCare(int carId) {
		if (this.careDao.getByCar_CarId(carId).size() != 0) {
			Care care = this.careDao.getByCar_CarId(carId).get(this.careDao.getByCar_CarId(carId).size() - 1);

			if (care.getFinishDate() == null) {
				return new ErrorResult(RentalMessages.ErrorIfCarIsNotAvailableToRent);
			}

		}
		return new SuccessResult();
	}
	
	//ödeme kaydedilsin mi methodu
	private Result checkCreditCardPaymentAndSavedControl(CreditCardDto creditCardDto, int customerId) {

		AddCreditCardRequest addCreditCardRequest = new AddCreditCardRequest();

		addCreditCardRequest.setCardName(creditCardDto.getCardName());
		addCreditCardRequest.setCardNumber(creditCardDto.getCardNumber());
		addCreditCardRequest.setExpiration(creditCardDto.getExpiration());
		addCreditCardRequest.setCvc(creditCardDto.getCvc());
		addCreditCardRequest.setCustomerId(customerId);

		return new SuccessResult(this.creditCardService.add(addCreditCardRequest).getMessage());
	}
	
	//pos işlemindeki limit için kontrol
	public Result checkPaymentControl(CreditCardDto creditCardDto, double price) {

		PosRequest posRequest = new PosRequest();
		posRequest.setCardName(creditCardDto.getCardName());
		posRequest.setCardNumber(creditCardDto.getCardNumber());
		posRequest.setExpiration(creditCardDto.getExpiration());
		posRequest.setCvc(creditCardDto.getCvc());
		posRequest.setPrice(price);

		if (!this.createPosService.payment(posRequest)) {
			return new ErrorResult(RentalMessages.ErrorMoneyIsNotEnoughToRentACar);
		}
		return new SuccessResult();
	}
	
	// arabanın total fiyatı
	private double getRentalTotalPrice(AddRentAlRequest addRentAlRequest) {

		Car car = this.carDao.getById(addRentAlRequest.getCarId());

		long totalRentalDay = ChronoUnit.DAYS.between(addRentAlRequest.getRentDate().toInstant(),
				addRentAlRequest.getReturnDate().toInstant());

		double totalPrice = car.getDailyPrice() * (int) totalRentalDay;

		return totalPrice;
	}
	
	//Hizmet bedelleri hesaplama
	private double checkCalculateTotalAmountOfRental(AddRentAlRequest addRentAlRequest) {

		Car car = this.carDao.getById(addRentAlRequest.getCarId());

		long rentDays = ChronoUnit.DAYS.between(addRentAlRequest.getRentDate().toInstant(),
				addRentAlRequest.getReturnDate().toInstant());

		double amount = rentDays * car.getDailyPrice();
		
		//Araç başlangıç şehrine teslim edilmediyse 500 tl fark alınacak
		if (addRentAlRequest.getReturnCity().equals(addRentAlRequest.getTakeCity()) == false) {
			amount += 500;

		}

		List<AdditionalServiceDto> additionalServiceDtos = addRentAlRequest.getAdditionalServiceDtos();

		for (AdditionalServiceDto additionalServiceDto : additionalServiceDtos) {

			AdditionalService additionalService = this.additionalServiceDao.getById(additionalServiceDto.getAdditionalId());

			double additionalServiceTotalPrice = additionalService.getAdditionalPrice() * rentDays;

			amount += additionalServiceTotalPrice;
		}

		return (double) amount;
	}
	
	private List<AdditionalService> convertFromAdditionalServiceDto(List<AdditionalServiceDto> additionalServiceDtos) {
		List<AdditionalService> additionalServices = new ArrayList<AdditionalService>();

		for (AdditionalServiceDto additionalServicedto : additionalServiceDtos) {
			additionalServices.add(this.additionalServiceDao.getById(additionalServicedto.getAdditionalId()));
		}

		return additionalServices;
	}
	
	 
	
}
		
