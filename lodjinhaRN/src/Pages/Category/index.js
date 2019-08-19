import React, { Component } from 'react';
import { FlatList, SafeAreaView, ActivityIndicator, Text, View } from 'react-native';

import ProductItem from '../../components/ProductItem';

const NUMBER_OF_REGISTERS_PER_PAGE = 20;

export default class Category extends Component {

  state = {
    products: [],
    pageNumber: 0,
    total: 0,
    loading: false
  }

  static navigationOptions = ({ navigation }) => ({
    title: navigation.state.params.category.descricao,
    headerTintColor: '#fff',
    headerStyle: {
      backgroundColor: '#5e2a84'
    }
  })

  async componentDidMount() {
    await this.loadProducts()
  }

  _keyExtractor = (item, index) => String(item.id);

  loadProducts = async (pageNumber = this.state.pageNumber) => {

    if(this.state.total && this.state.pageNumber > this.state.total) return;

    this.setState({
      loading: true
    })

    const offset = pageNumber * 20;
    const response = await fetch(
      `https://alodjinha.herokuapp.com/produto/?offset=${offset}&limit=20&categoriaId=${this.props.navigation.state.params.category.id}`
    );
    const products = await response.json();

    this.setState({
        total: Math.floor(products.total / NUMBER_OF_REGISTERS_PER_PAGE),
        products: [ ...this.state.products, ...products.data],
        pageNumber: this.state.pageNumber + 1,
        loading: false
    })
  }

  renderEmpty = () => (
    <View style={{ flex: 1, alignItems:'center', justifyContent: 'center' }}>
      <Text>Nenhum resultado encontrado.</Text>
    </View>
  )

  render() {
    return (
      <SafeAreaView>
        <FlatList
          data={this.state.products}
          keyExtractor={this._keyExtractor}
          onEndReached={() => this.loadProducts()}
          onEndReachedThreshold={0.1}
          ListEmptyComponent={this.renderEmpty}
          ListFooterComponent={this.state.loading && <ActivityIndicator />}
          renderItem={({ item }) => <ProductItem item={item} />} />
      </SafeAreaView>
    );
  }
}
