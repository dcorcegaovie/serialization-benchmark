package com.daniel.binary.serializers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    @JsonProperty("id")
    public String id;
    @JsonProperty("site_id")
    public String siteId;
    @JsonProperty("title")
    public String title;
    @JsonProperty("subtitle")
    public String subtitle;
    @JsonProperty("seller_id")
    public Integer sellerId;
    @JsonProperty("category_id")
    public String categoryId;
    @JsonProperty("official_store_id")
    public String officialStoreId;
    @JsonProperty("price")
    public Float price;
    @JsonProperty("base_price")
    public Float basePrice;
    @JsonProperty("original_price")
    public Float originalPrice;
    @JsonProperty("currency_id")
    public String currency_id;
    @JsonProperty("initial_quantity")
    public Integer initialQuantity;
    @JsonProperty("available_quantity")
    public Integer availableQuantity;
    @JsonProperty("sold_quantity")
    public Integer soldQuantity;
    @JsonProperty("buying_mode")
    public String buyingMode;
    @JsonProperty("listing_type_id")
    public String listingTypeId;
    @JsonProperty("start_time")
    public String startTime;
    @JsonProperty("historical_start_time")
    public String historicalStartTime;
    @JsonProperty("stop_time")
    public String stopTime;
    @JsonProperty("condition")
    public String condition;
    @JsonProperty("permalink")
    public String permalink;
    @JsonProperty("thumbnail")
    public String thumbnail;
    @JsonProperty("secure_thumbnail")
    public String secureThumbnail;
    @JsonProperty("pictures")
    public Picture[] pictures;
    @JsonProperty("video_id")
    public String videoId;
    @JsonProperty("descriptions")
    public Description[] descriptions;
    @JsonProperty("accepts_mercadopago")
    public Boolean acceptsMercadopago;
    @JsonProperty("shipping")
    public Shipping shipping;
    @JsonProperty("international_delivery_mode")
    public String intDelMode;
    @JsonProperty("seller_address")
    public Address sellerAddress;
    @JsonProperty("geolocation")
    public Geolocation geolocation;
    @JsonProperty("listing_source")
    public String ls;
    @JsonProperty("status")
    public String status;
    @JsonProperty("tags")
    public String[] tags;
    @JsonProperty("catalog_product_id")
    public String cpid;
    @JsonProperty("domain_id")
    public String domid;
    @JsonProperty("parent_item_id")
    public String paritemid;
    @JsonProperty("automatic_relist")
    public Boolean automatic_relist;
    @JsonProperty("date_created")
    public String date_created;
    @JsonProperty("last_updated")
    public String last_updated;
}

class Picture {
    @JsonProperty("id")
    public String id;
    @JsonProperty("url")
    public String url;
    @JsonProperty("secure_url")
    public String secure_url;
    @JsonProperty("size")
    public String size;
    @JsonProperty("max_size")
    public String maxSize;
    @JsonProperty("quality")
    public String quality;
}

class Description {
    @JsonProperty("id")
    public String id;
}

class Shipping {
    @JsonProperty("mode")
    public String mode;
    @JsonProperty("local_pick_up")
    public Boolean lpu;
    @JsonProperty("free_shipping")
    public Boolean fs;
    @JsonProperty("store_pick_up")
    public Boolean spu;
}

class Address {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("comment")
    public String comment;
    @JsonProperty("address_line")
    public String al;
    @JsonProperty("zip_code")
    public String zipCode;
    @JsonProperty("city")
    public City city;
    @JsonProperty("state")
    public State state;
    @JsonProperty("country")
    public Country country;
    @JsonProperty("latitude")
    public String lat;
    @JsonProperty("longitud")
    public String lon;
}

class City {
    @JsonProperty("id")
    public String id;
    @JsonProperty("name")
    public String name;
}

class State {
    @JsonProperty("id")
    public String id;
    @JsonProperty("name")
    public String name;
}

class Country {
    @JsonProperty("id")
    public String id;
    @JsonProperty("name")
    public String name;
}

class Geolocation {
    @JsonProperty("latitude")
    public String lat;
    @JsonProperty("longitud")
    public String lon;
}