import {Cafeteria, Restaurant, Brush, Bar, Book, Java, Anchor, Spa, Bus, Music, Cart, IceCream, Car, Shop, Ticket, Bike } from "grommet-icons"
import React from "react";

export function getAttractionTypes(color, size){
    return [
        { icon: <Bar color={color} size={size} />, key: "Bar-logo", id: "Bar-id", value: "bar", a11yTitle: "bar" , caption: "Bar"},
        { icon: <Cafeteria color={color} size={size} />, key: "Cafeteria-logo", id: "Cafe-id", value: "restaurant", caption: "Restaurant" },
        { icon: <Book color={color} size={size} />, key: "Book-logo", id: "book-id", value: "library", caption: "Library"},
        { icon: <Car color={color} size={size} />, key: "Car-logo", id: "car-id", value: "car rental", caption: "Car Rental"},
        { icon: <Ticket color={color} size={size} />, key: "Ticket-logo", id: "ticket-id", value: "ticket rental", caption: "Tickets" },
        { icon: <Bike color={color} size={size} />, key: "Bike-logo", id: "bike-id", value: "bike rental", caption: "Bike Rental" },
        { icon: <Brush color={color} size={size} />, key: "art-logo", id: "art-id", value: "art centre", caption: "Art Centre" },
        { icon: <Anchor color={color} size={size} />, key: "boat-logo", id: "boat-id", value: "boat rental", caption: "Boat Rental" },
        { icon: <Restaurant color={color} size={size} />, key: "bakery-logo", id: "bakery-id", value: "bakery rental", caption: "Bakery" },
        { icon: <Spa color={color} size={size} />, key: "beauty-logo", id: "beauty-id", value: "Beauty", caption: "Beauty"},
        { icon: <Bus color={color} size={size} />, key: "bus-logo", id: "bus-id", value: "bus station", caption: "Bus Station"},
        { icon: <Java color={color} size={size} />, key: "Cafe-logo", id: "cafe-id", value: "cafe", caption: "Coffee Shop" },
        { icon: <Shop color={color} size={size} />, key: "cloth-logo", id: "cloth-id", value: "clothing store", caption: "Clothing" },
        { icon: <Music color={color} size={size} />, key: "music-logo", id: "music-id", value: "music venue", caption: "Music Venue" },
        { icon: <Cart color={color} size={size} />, key: "store-logo", id: "store-id", value: "store", caption: "Shopping" },
        { icon: <IceCream color={color} size={size} />, key: "icecream-logo", id: "icecream-id", value: "ice cream", caption: "Ice Cream" }
    ]
}

export function getAttractionType(key, color, size){
    return getAttractionTypes(color, size).filter(attr => attr.key === key)[0];
}

export function getAttractionByValue(value, color, size){
    return getAttractionTypes(color, size).filter(attr => attr.value === value)[0];
}
