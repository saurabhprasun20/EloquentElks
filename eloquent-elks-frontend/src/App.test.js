import { render, screen } from '@testing-library/react';
import App from './App';
import {AirBnBInformationLayer} from "./components/AirBnBInformationLayer";
import {LandingPage} from "./components/LandingPage";
import {getAttractionByValue, getAttractionTypes} from "./resources/attractions";


test('renders recommendation button', () => {
  render(<App />);
  const linkElement = screen.getByText(/Load recommendation/i);
  expect(linkElement).toBeInTheDocument();
});

test('Renders the information layer', () => {
  let testAirBnb = {
    "name": "Cozy Entire Floor of Brownstone",
    "longitude": -73.95976,
    "latitude": 40.68514,
    "roomType": "Entire home/apt",
    "price": 89.0,
    "hostName": "LisaRoxanne"
  }

  render(<AirBnBInformationLayer content={testAirBnb}/>);
  const text = screen.getByText("Here is some basic information about the apartment by the owner.")
  expect(text).toBeInTheDocument();
});

test("Tests the getAttractionByValue function", () => {
  let attraction = getAttractionByValue("restaurant", "blue", 10)
  expect(attraction.value).toBe("restaurant")
});

test('Renders the landing page', () => {
  let attraction = getAttractionTypes("blue", 10)

  render(<LandingPage key={attraction.length}
                      attractionTypes={attraction}/>)
  const occurences = screen.getAllByText("Welcome to NYC! Please select your preferred types of attractions.")
  expect(occurences.length).toBeGreaterThanOrEqual(1)
});


