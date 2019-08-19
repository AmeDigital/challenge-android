import React, { Component } from 'react';
import { SafeAreaView } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';

import { Text, Image, View, TouchableOpacity } from 'react-native';

import Styles from './styles';

export default class About extends Component {
  static navigationOptions = ({ navigation }) => ({
    headerTitle: (
      <View style={Styles.headerTitleContainer}>
        <Text style={Styles.headerTitle}>Sobre</Text>
      </View>
    ),
    headerLeft: (
      <TouchableOpacity 
        style={Styles.headerMenu}
        onPress={() => navigation.openDrawer()}>
        <Icon name="menu" color="#FFF" size={25}/>
      </TouchableOpacity>
    ),
    title: 'a lodjinha'
  })

  render() {
    return (
      <SafeAreaView style={Styles.container}>
        <View style={Styles.content}>
          <Image style={Styles.logoImage} source={require('../../assets/images/logo_sobre.png')} />
          <Text style={Styles.logo}>a Lodjinha</Text>
        </View>
        <View style={Styles.footer}>
          <Text>Leonardo Almeida</Text>
          <Text>19 de agosto de 2019</Text>
        </View>
      </SafeAreaView>
    );
  }
}
