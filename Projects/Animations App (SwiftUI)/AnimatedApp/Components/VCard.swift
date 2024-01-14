//
//  VCard.swift
//  AnimatedApp
//
//  Created by Simeon Rubin on 14/1/2024.
//

import SwiftUI

struct VCard: View {
    var course: Course
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(course.title)
                .customFont(.title2)
                .frame(maxWidth: 170, alignment: .leading)
            Text(course.subtitle)
                .customFont(.subheadline)
                .frame(maxWidth: .infinity, alignment: .leading)
                .opacity(0.7)
            Text(course.caption.uppercased())
                .customFont(.footnote2)
            Spacer()
            HStack {
                ForEach(Array([4,5,6].shuffled().enumerated()), id: \.offset) { index, number in
                    Image("Avatar \(number)")
                        .resizable()
                        .frame(width: 44, height: 44)
                        .mask(Circle())
                        .offset(x: CGFloat(index * -20))
                }
            }
        }
        .foregroundColor(.white)
        .padding(30)
        .frame(width: 260, height: 310)
        .background(.linearGradient(colors: [course.color,course.color.opacity(0.5)], startPoint: .topLeading, endPoint: .bottomTrailing))
        .mask(RoundedRectangle(cornerRadius: 30, style: .continuous))
        .shadow(color: course.color.opacity(0.3), radius: 2, x: 0, y:1)
        .overlay(
            course.image
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topTrailing)
                .padding(20)
        )
    }
}

#Preview {
    VCard(course: courses[0])
}
