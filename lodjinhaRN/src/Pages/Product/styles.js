import { StyleSheet } from 'react-native';

export const Styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#FCFCFC'
  },
  imageContainer: {
    flex: 1,
  },
  image: {
    height: 200
  },
  titleContainer: {
    flex: 0,
    padding: 16,
    borderBottomWidth: 1,
    borderBottomColor: '#d5d5d5'
  },
  title: {
    fontSize: 18,
    fontFamily: 'Roboto',
    fontWeight: 'bold'
  },
  description: {
    width: 100,
    height: 100,
    fontFamily: 'Roboto',
    fontSize: 40
  },
  priceContainer: {
    flex: 0,
    padding: 16,
    borderBottomWidth: 1,
    borderBottomColor: '#d5d5d5',
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'flex-end'
  },
  descriptionContainer: {
    flex: 1,
    padding: 16,
    marginBottom: 72
  },
  oldPrice: {
    fontFamily: 'Roboto',
    color: '#aaaaaa',
    textDecorationLine: 'line-through'
  },
  currentPrice: {
    fontFamily: 'Roboto',
    fontSize: 20,
    fontWeight: 'bold',
    color: '#f15025'
  },
})