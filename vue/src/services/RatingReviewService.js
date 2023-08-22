import axios from 'axios';
const http =axios.create({
    baseURL: "http://localhost:9000"
});
export default {

  getBeerAvgRating(beerId) {
      return http.get(`/beers/${beerId}/rating`);
  },
  getBeerReviews(beerId) {
      return http.get(`/beers/${beerId}/reviews`);
  },
  getRatingsByUser(userId) {
      return http.get(`/beers/ratings/${userId}`);
  },
  addBeerReview(newBeerReview) {
      return http.post(`/beers/reviews`, newBeerReview);
  },
  addBeerRatings(newBeerRatings) {
      return http.post(`/beers/ratings`, newBeerRatings);
  },
  getRatingsAndBeersByUser(userId) {
      return http.get(`/ratings/${userId}`);
  },
  getReviewsAndBeersByUser(userId) {
    return http.get(`/reviews/${userId}`);
  }

}
