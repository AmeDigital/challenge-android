import React from 'react';
import { StatusBar } from 'react-native';

import Routes from './routes';

function App() {
	return (
		<React.Fragment>
			<StatusBar backgroundColor="#3D2655" translucent />
			<Routes />
		</React.Fragment>
	);
}

export default App;
