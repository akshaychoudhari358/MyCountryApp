package master.akshay.mycountryapp.di;

import dagger.Module;
import dagger.Provides;
import master.akshay.mycountryapp.model.CountriesAPI;
import master.akshay.mycountryapp.model.CountriesService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ApiModule {

    public static final String BASE_URL = "https://raw.githubusercontent.com";

    @Provides
    public CountriesAPI provideCountriesApi() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(CountriesAPI.class);

    }

    @Provides
    public CountriesService provideCountriesService(){
        return CountriesService.getInstance();
    }
}
