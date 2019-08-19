import React, { Component } from 'react';
import { View, ScrollView, Image, Text, SafeAreaView, Alert } from 'react-native';
import HTML from 'react-native-render-html';
import ActionButton from 'react-native-action-button';

import { formatNumber } from '../../helper';
import { Styles } from './styles';

export default class Product extends Component {
  static navigationOptions = {
    headerStyle: {
      elevation: 0,
      shadowOpacity: 0,
      borderBottomWidth: 0,
      backgroundColor: '#FCFCFC'
    }
  }

  handleClick = async (product) => {
    const response = await fetch(`https://alodjinha.herokuapp.com/produto/${product.id}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      }
    });
    const body = await response.json();
    
    if(body && body.result === 'success') {
      Alert.alert('', 'Produto reservado com sucesso!', [
        {text: 'ok' , onPress: () => this.props.navigation.goBack() }
      ])
    }
  }
    

  render() {
    const { product } = this.props.navigation.state.params;
    return (
      <SafeAreaView style={Styles.container}>
        <ScrollView style={Styles.container}>
          <View style={Styles.imageContainer}>
            <Image style={Styles.image} resizeMode="contain" source={{ uri: product.urlImagem }} />
          </View>
          <View style={Styles.titleContainer}>
            <Text style={Styles.title}>{product.nome}</Text>
          </View>
          <View style={Styles.priceContainer}>
            <Text style={Styles.oldPrice}>De: {formatNumber(product.precoDe)}</Text>
            <Text style={Styles.currentPrice}>Por {formatNumber(product.precoPor)}</Text>
          </View>
          <View style={Styles.descriptionContainer}>
            <HTML html={product.descricao} />
          </View>
        </ScrollView>
        <ActionButton 
        buttonColor="#5e2a84" 
        renderIcon={() => <Image style={{ width: 30, height: 20}} source={require('../../assets/images/check_button.png')} />} 
        onPress={async () => await this.handleClick(product)}></ActionButton>
      </SafeAreaView>
    );
  }
}
