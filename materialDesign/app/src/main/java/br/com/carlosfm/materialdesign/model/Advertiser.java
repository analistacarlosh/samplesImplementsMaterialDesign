package br.com.carlosfm.materialdesign.model;

import java.io.Serializable;

/**
 * Created by carlosfmr on 5/20/15.
 */
public class Advertiser implements Serializable {

    private String id;
    private String name;
    private String address;
    private String numberAddress;
    private String district;
    private String city;
    private String phoneNumber;
    private String phones;
    private String data;
    private String data2;
    private String email;
    private String sector;
    private String sectors;
    private String site;
    private String products;
    private String priority;
    private String advertising;
    private String delivery;
    private String ofertas;
    private String url;

    public Advertiser(String id,
                    String name,
                    String address,
                    String numberAddress,
                    String district,
                    String city,
                    String phoneNumber,
                    String phones,
                    String data,
                    String data2,
                    String email,
                    String sector,
                    String sectors,
                    String site,
                    String products,
                    String priority,
                    String advertising,
                    String delivery,
                    String ofertas,
                    String url
                    ){
        this.id = id;
        this.name = name;
        this.address = address;
        this.numberAddress = numberAddress;
        this.district = district;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.phones = phones;
        this.data = data;
        this.data2 = data2;
        this.email = email;
        this.sector = sector;
        this.sectors = sectors;
        this.site = site;
        this.products = products;
        this.priority = priority;
        this.advertising = advertising;
        this.delivery = delivery;
        this.ofertas = ofertas;
        this.url = url;

    }
}
