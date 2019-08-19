import { StyleSheet } from 'react-native';

const Styles = StyleSheet.create({
  productItemContainer: {
    flex: 1,
    flexDirection: 'row',
    borderBottomWidth: 1,
    borderBottomColor: '#d5d5d5',
    padding: 8
  },
  productItemPrice: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'flex-end'
  },
  productItemImage: {
    height: 100,
    width: 100
  },
  productItemTitle: {
    fontFamily: 'Roboto',
    fontSize: 18
  },
  productItemOldPrice: {
    fontFamily: 'Roboto',
    color: '#aaaaaa',
    textDecorationLine: 'line-through'
  },
  productItemCurrentPrice: {
    fontFamily: 'Roboto',
    fontSize: 20,
    fontWeight: 'bold',
    color: '#f15025'
  },
  productItemInfos: {
    flex: 1,
    justifyContent: 'space-between',
    paddingHorizontal: 8
  }
});

export default Styles;