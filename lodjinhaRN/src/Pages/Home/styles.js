import { StyleSheet } from 'react-native';

export const Styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#FCFCFC'
  },
  categoriesContainer: {
    flex: 1,
    borderBottomWidth: 1,
    borderBottomColor: '#d5d5d5'
  },
  bannerContainer: {
    height: 150
  },
  bestSellersContainer: {
    flex: 1
  },
  slideImage:{
   height: 150
  },
  titleContainer: {
    borderBottomWidth: 1,
    borderBottomColor: '#d5d5d5',
    padding: 10
  },
  title: {
    fontFamily: 'Roboto',
    fontWeight: 'bold',
    fontSize: 16,
    color: '#2d3142'
  },
  categoryItemContainer: {
    flex: 2,
    alignItems: 'center',
    justifyContent: 'center',
    paddingHorizontal: 8,
    paddingTop: 8
  },
  categoryItemImage: {
    height: 50,
    width: 50
  },
  categoryItemDescription: {
    fontFamily: 'Roboto'
  }
})