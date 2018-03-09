package com.example.air.cryptocurrency.data;

import com.example.air.cryptocurrency.data.api.CurrencyApi;
import com.example.air.cryptocurrency.model.currency_list.AllCurrencyResponse;
import com.example.air.cryptocurrency.model.selected_currency.SelectedCurrencyResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Observable;

import static org.mockito.Mockito.*;

public class DataManagerTest {
    @Mock
    CurrencyApi retrofit;
    @InjectMocks
    DataManager dataManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCurrency() throws Exception {
        Observable<ArrayList<AllCurrencyResponse>> result = dataManager.getAllCurrency("currency", "limit");
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetSelectedCurrency() throws Exception {
        Observable<ArrayList<SelectedCurrencyResponse>> result = dataManager.getSelectedCurrency("idCurrency", "currency");
        Assert.assertEquals(null, result);
    }

}