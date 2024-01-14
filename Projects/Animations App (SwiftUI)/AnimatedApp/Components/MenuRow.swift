//
//  MenuRow.swift
//  AnimatedApp
//
//  Created by Simeon Rubin on 14/1/2024.
//

import SwiftUI

struct MenuRow: View {
    var item: MenuItem
    @Binding var selectedMenu: SelectedMenu
    
    var body: some View {
        HStack (spacing: 14) {
            item.icon.view()
                .frame(width: 32, height: 32)
                .opacity(0.6)
            Text(item.text)
                .customFont(.headline)
        }
        .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, alignment: .leading)
        .padding(12)
        .background(
            RoundedRectangle(cornerRadius: 10, style:
                    .continuous)
            .fill(.blue)
            .frame(maxWidth: selectedMenu == item.menu ? .infinity : 0)
            .frame(maxWidth: .infinity, alignment: .leading)
        )
        .background(Color("Background 2"))
        .onTapGesture {
            item.icon.setInput("active", value: true)
            DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
                item.icon.setInput("active", value: false)
            }
            withAnimation(.timingCurve(0.2,0.8,0.2,1)) {
                selectedMenu = item.menu
            }
        }    }
}

#Preview {
    MenuRow(item: menuItems[0], selectedMenu: .constant(.home))
}
