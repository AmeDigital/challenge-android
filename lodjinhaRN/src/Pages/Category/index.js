import React, { Component } from 'react';
import { FlatList, SafeAreaView} from 'react-native';

import ProductItem from '../../components/ProductItem';

export default class Category extends Component {

  state = {
    products: []
  }

  static navigationOptions = ({ navigation }) => ({
    title: navigation.state.params.category.descricao,
    headerTintColor: '#fff',
    headerStyle: {
      backgroundColor: '#5e2a84'
    }
  })

  async componentDidMount() {
    await this.getProducts()
  }

  _keyExtractor = (item, index) => String(item.id);

  getProducts = async () => {
    const response = await fetch(`https://alodjinha.herokuapp.com/produto/?categoriaId=${this.props.navigation.state.params.category.id}`);
    const products = await response.json();

    this.setState({
        products: products.data
    })
  }

  render() {
    return (
      <SafeAreaView>
        <FlatList
          data={this.state.products}
          keyExtractor={this._keyExtractor}
          renderItem={({ item }) => <ProductItem item={item} />} />
      </SafeAreaView>
    );
  }
}
