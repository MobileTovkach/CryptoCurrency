package com.example.air.cryptocurrency.adapter;

import com.example.air.cryptocurrency.model.currency_list.AllCurrencyResponse;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import timber.log.Timber;

public class CurrencyAdapterTest {
    @Mock
    ArrayList<AllCurrencyResponse> data;

    CurrencyHolder.RangCallback callback = new CurrencyHolder.RangCallback() {
        @Override
        public void getPosition(@NotNull String rank) {
            Timber.d("Rank %s", rank);
        }
    };

    CurrencyAdapter currencyAdapter;


    @Before
    public void setUp() {
        currencyAdapter = new CurrencyAdapter(callback);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetItemCount() throws Exception {
        int result = currencyAdapter.getItemCount();
        Assert.assertEquals(0, result);
    }

    @Test
    public void testSetData() throws Exception {
        currencyAdapter.setData(new ArrayList<AllCurrencyResponse>(Arrays.asList(new AllCurrencyResponse("priceUsd", "symbol", "lastUpdated", "totalSupply", "jsonMember24hVolumeUsd", "priceBtc", "availableSupply", "marketCapUsd", "percentChange1h", "percentChange24h", "name", "maxSupply", "rank", "id", "percentChange7d", "priceEur", "jsonMember24hVolumeEur", "marketCapEur", "priceCny", "jsonMember24hVolumeCny", "marketCapCny"))));
    }

}