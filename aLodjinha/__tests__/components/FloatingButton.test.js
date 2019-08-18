import React from 'react';

import renderer from 'react-test-renderer';

import FloatingButton from '../../src/components/FloatingButton';

describe('Component FloatingButton', () => {
	test('should render without erros', () => {
		const tree = renderer.create(<FloatingButton />).toJSON();
		expect(tree).toMatchSnapshot();
	});
});
