package cis3334.java_pizzaorderstart;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jspecify.annotations.NonNull;

import java.io.Closeable;
import java.util.List;

public class MainViewModel extends ViewModel {

    PizzaRepository pizzaOrder;
    List<String> pizzasOrder;

    public MainViewModel(@NonNull Application application) {
        super((Closeable) application);
        Log.d("CIS 3334", "MainViewModel construction with Application link");
        pizzaOrder = new PizzaRepository(application);
        Log.d("CIS 3334", "MainViewModel construction PizzaRepository created");

        pizzasOrder = pizzaOrder.getOrder();
    }

    // For live data updates from https://developer.android.com/topic/libraries/architecture/livedata
    private MutableLiveData<String> orderStatus;
    public MutableLiveData<String> getOrderStatus() {
        if (orderStatus == null) {
            orderStatus = new MutableLiveData<String>();
        }
        return orderStatus;
    }


    PizzaOrder myPizzaOrder = new PizzaOrder();

    public void placeOrder(String topping, Integer size) {
        myPizzaOrder.OrderPizza(topping, size);
    }

    // Get the order as a sring
    public String getOrder() {
        String orderDescription = "";
        List<String> pizzaList = myPizzaOrder.getOrder();
        for (String strPizza:pizzaList ) {
            orderDescription += strPizza + "\n";
        }
        return orderDescription;
    }


}