package master.akshay.mycountryapp.di;

import dagger.Component;
import master.akshay.mycountryapp.model.CountriesService;
import master.akshay.mycountryapp.viewmodel.ListViewModel;

@Component(modules = {ApiModule.class})
public interface ApiComponent{

    void inject(CountriesService service);

    void inject(ListViewModel listViewModel);
}

