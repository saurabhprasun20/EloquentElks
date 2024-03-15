import img from "../resources/mock_nyc_airbnb.jpg"
import img2 from "../resources/mock_nyc_airbnb_2.jpg"
import img3 from "../resources/mock_nyc_airbnb_3.jpg"
import img4 from "../resources/mock_nyc_airbnb_4.jpg"
import img5 from "../resources/mock_nyc_airbnb_5.jpg"

// Returns a random one of five images for the information layer
export const getAptImage = (index) => {
    let aptImageList = [img, img2, img3, img4, img5]
    return aptImageList[index]
}
