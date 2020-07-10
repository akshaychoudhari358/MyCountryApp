package master.akshay.mycountryapp.model;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import master.akshay.mycountryapp.di.DaggerApiComponent;

public class CountriesService {

    private static CountriesService instance;

    @Inject
    public CountriesAPI api;

    private  CountriesService(){
        DaggerApiComponent.create().inject(this);
    }

    public  static CountriesService getInstance(){
        if(instance == null){
            instance = new CountriesService();
        }
        return instance;
    }

    public Single<List<CountryModel>> getCountries(){
        return api.getCountries();
    }
}
