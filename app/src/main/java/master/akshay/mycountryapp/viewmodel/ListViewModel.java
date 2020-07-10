package master.akshay.mycountryapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import master.akshay.mycountryapp.di.DaggerApiComponent;
import master.akshay.mycountryapp.model.CountriesService;
import master.akshay.mycountryapp.model.CountryModel;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<List<CountryModel>>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    @Inject
    public CountriesService countriesService;

    private CompositeDisposable disposable = new CompositeDisposable();

    public ListViewModel(){
        super();
        DaggerApiComponent.create().inject(this);
    }

    public void refresh() {
        fetchCountries();
    }

    public void fetchCountries() {
       /* CountryModel country1 = new CountryModel("India", "Delhi", "");
        CountryModel country2 = new CountryModel("Pakistan","Lahore", "");
        CountryModel country3 = new CountryModel("Bangaldesh", "Dhaka","");

        List<CountryModel> list = new ArrayList<>();
        list.add(country1);
        list.add(country2);
        list.add(country3);
        list.add(country1);
        list.add(country2);
        list.add(country3);
        list.add(country1);
        list.add(country2);
        list.add(country3);
        list.add(country1);
        list.add(country2);
        list.add(country3);

        countries.setValue(list);
        countryLoadError.setValue(false);
        loading.setValue(false);*/

        loading.setValue(true);
        disposable.add(
                countriesService.getCountries()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {
                            @Override
                            public void onSuccess(List<CountryModel> countryModels) {
                                countries.setValue(countryModels);
                                countryLoadError.setValue(false);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                countryLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
