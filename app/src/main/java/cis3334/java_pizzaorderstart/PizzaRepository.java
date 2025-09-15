package cis3334.java_pizzaorderstart;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PizzaRepository {
    private PizzaDao pizzaDao;
    private List<Pizza> pizzasInOrder;

    PizzaRepository(Application application) {
        PizzaDatabase db = PizzaDatabase.getDatabase(application);
        Log.d("CIS 3334", "PizzaRepository Setting up the Dao and  database");
        pizzaDao = db.pizzaDao();
        Log.d("CIS 3334", "PizzaRepository Dao created");
        pizzasInOrder = new ArrayList<Pizza>();
        //pizzasInOrder = heartrateDao.getAll();
        //Log.d("CIS 3334", "PizzaRepository pizzas retreived from Dao, size = " + pizzasInOrder.size());

    }

    public String OrderPizza(Pizza newPizza){
        Log.d("CIS 3334", "PizzaRepository OrderPizza creating pizzza");
        //Pizza newPizza = new Pizza(topping, size);
        Log.d ("CIS 3334", newPizza.toString());
        // ROOM -- Add insert into database
        PizzaDatabase.databaseWriteExecutor.execute(() -> {
            Log.d("CIS 3334", "PizzaRepository - OrderPizza:databaseWriteExecutor - starting up heartrateDao");
            pizzaDao.insert(newPizza);
            Log.d("CIS 3334", "PizzaRepository - OrderPizza:databaseWriteExecutor -Inserting pizza into database");
            //Log.d ("CIS 3334", newPizza.toString());
        });
        pizzasInOrder.add(newPizza);
        return newPizza.toString();             // return a description of what was ordered
    }


    /***
     * Retrieve the full order
     * @return a list of pizzas, each one describe by a single string
     */
    public List<String> getOrder() {
        PizzaDatabase.databaseWriteExecutor.execute(() -> {
            Log.d("CIS 3334", "PizzaRepository - getOrder:databaseWriteExecutor - starting up");
            pizzasInOrder = pizzaDao.getAll();              // ROOM - add this
            Log.d("CIS 3334", "PizzaRepository - getOrder:databaseWriteExecutor - got order");
            //Log.d ("CIS 3334", newPizza.toString());
        });
        Log.d("CIS 3334", "PizzaRepository - getOrder: pizzaOrder size = "+pizzasInOrder.size());

        ArrayList<String> strOrder = new ArrayList<String>();
        for (Pizza p:pizzasInOrder ){
            strOrder.add(p.toString());
        }
        return strOrder;
    }
}
