//
//  CountrySelection.swift
//  iosApp
//
//  Created by fturkan on 11.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct CountrySelection: View {
    var selectedCountry: Country
    var onClick: () -> Void
    var enabled: Bool = true
    
    var body: some View {
        VStack(alignment: .center, spacing: 0) {
            Text("\(selectedCountry.toFlagEmoji()) +\(selectedCountry.phoneCode)")
                .font(.body)
                .padding(.horizontal, 16)
        }
        .frame(height: 60)
        .background(Color.gray.opacity(0.09))
        .cornerRadius(12)
        .onTapGesture {
            if enabled {
                onClick()
            }
        }
    }
}


struct CountrySelectionSheetContent: View {
    var countries: [Country]
    var onCountrySelect: (Country) -> Void
    
    @State private var searchText: String = ""
    
    var body: some View {
        VStack(alignment: .center, spacing: 24) {
            TextField(
                Strings().get(
                    id: SharedRes.strings().search_country_code,
                    args: []
                ),
                text: $searchText
            )
            .padding(EdgeInsets(top: 0, leading: 16, bottom: 0, trailing: 16))
            .textFieldStyle(RoundedBorderTextFieldStyle())
            
            ScrollView {
                LazyVStack {
                    ForEach(filteredCountries, id: \.self) { country in
                        CountrySelectionItem(country: country)
                            .onTapGesture {
                                onCountrySelect(country)
                            }
                        Divider()
                    }
                }
            }
            .padding(EdgeInsets(top: 0, leading: 16, bottom: 0, trailing: 16))
        }
        .background(Color(UIColor.systemBackground))
        .cornerRadius(12)
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .padding(24)
    }
    
    var filteredCountries: [Country] {
        countries.filter { country in
            country.hasText(text: searchText)
        }
    }
}

struct CountrySelectionItem: View {
    var country: Country
    
    var body: some View {
        HStack(alignment: .center, spacing: 0) {
            Text("\(country.toFlagEmoji()) \(country.name)")
                .font(.body)
            
            Spacer()
            
            Text("+\(country.phoneCode)")
                .foregroundColor(Color.gray)
        }
        .frame(height: 56)
    }
}
