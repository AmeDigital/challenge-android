import React, { Component } from 'react';
import {Text, View,} from 'react-native';
import { createBottomTabNavigator, createAppContainer, createStackNavigator , createDrawerNavigator} from 'react-navigation';
import Orientation from 'react-native-orientation-locker';

import Styles, { minimumDisplay , DeviceWidth } from '../Styles' ;
const { style } = Styles;


import HomeScreen from './screens/HomeScreen';
import AboutScreen from './screens/AboutScreen';
import CategoryScreen from './screens/CategoryScreen';
import ProductScreen from './screens/ProductScreen';
import MenuScreen from './screens/MenuScreen';


const homeStack = createStackNavigator({
    dashboard: { screen: HomeScreen },
    about: { screen: AboutScreen },
    category: { screen: CategoryScreen },
    product : {screen: ProductScreen},
    menu: {screen: MenuScreen},
  
    initialRouteName: 'dashboard'
})

const HomeNavigation = createDrawerNavigator(
    {
      homeStack: { screen: homeStack },
    },
    {
      initialRouteName: 'homeStack',
      drawerWidth: DeviceWidth <= minimumDisplay ? 300 : 350,
      drawerPosition: 'left',
      drawerType: 'slide',
      contentComponent: MenuScreen
    }
  )
  

class lodjinha extends Component {

    state = { tabBarVisible: false }
  

    async componentDidMount(){
        Orientation.lockToPortrait();  
    }

    render(){
        const MainNavigator = createBottomTabNavigator(
            {
                home: { screen: HomeNavigation, navigationOptions: { tabBarVisible: false } },
                about: { screen: AboutScreen, navigationOptions: { tabBarVisible: false } },
                menu: { screen: MenuScreen, navigationOptions: { tabBarVisible: false } },
            },
            {
            tabBarOptions: {
                showLabel: false,
                showIcon: false,
                style: {visible: false},
                tabStyle: { visible: false }
            },
            initialRouteName: 'home',
            }
        )

        const AppContainer = createAppContainer(MainNavigator);
        
        return(
            <View style={{flex:1}}>
                <AppContainer  />
            </View>
        )      
    }
}

export default lodjinha;
